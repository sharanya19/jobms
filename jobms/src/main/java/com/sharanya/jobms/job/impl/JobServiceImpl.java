package com.sharanya.jobms.job.impl;


import com.sharanya.jobms.clients.CompanyClient;
import com.sharanya.jobms.clients.ReviewClient;
import com.sharanya.jobms.dto.JobDTO;
import com.sharanya.jobms.job.Job;
import com.sharanya.jobms.job.JobRepository;
import com.sharanya.jobms.job.JobService;
import com.sharanya.jobms.job.external.Company;
import com.sharanya.jobms.job.external.Review;
import com.sharanya.jobms.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class JobServiceImpl implements JobService {


    JobRepository jobRepository;

    private CompanyClient companyClient;
    private ReviewClient reviewClient;

    @Autowired
    RestTemplate restTemplate;

    public JobServiceImpl(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    @Override
    @CircuitBreaker(name = "companyBreaker")
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobWithCompanies = new ArrayList<>();
        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private JobDTO convertToDto(Job job){
            Company company = companyClient.getCompany(job.getCompanyId());
            List<Review> review = reviewClient.getReviews(job.getCompanyId());
            JobDTO jobWithCompany = JobMapper.mapToJobWithCompany(job,company, review);
            return jobWithCompany;
    }


    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobDTO getJobById(Long id) {

        Job job = jobRepository.findById(id).orElse(null);
        assert job != null;
        return convertToDto(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {

        Optional<Job> optionalJob = jobRepository.findById(id);

            if(optionalJob.isPresent()){
                Job job = optionalJob.get();
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setLocation(updatedJob.getLocation());
                jobRepository.save(job);
                return true;
            }
        return false;
    }


}
