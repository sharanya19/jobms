package com.sharanya.jobms.mapper;

import com.sharanya.jobms.dto.JobDTO;
import com.sharanya.jobms.job.Job;
import com.sharanya.jobms.job.external.Company;
import com.sharanya.jobms.job.external.Review;

import java.util.List;

public class JobMapper {

    public static JobDTO mapToJobWithCompany(Job job, Company
            company, List<Review> reviewList){

        JobDTO jobWithCompany = new JobDTO();
        jobWithCompany.setCompany(company);
        jobWithCompany.setId(job.getId());
        jobWithCompany.setTitle(job.getTitle());
        jobWithCompany.setDescription(job.getDescription());
        jobWithCompany.setMinSalary(job.getMinSalary());
        jobWithCompany.setMaxSalary(job.getMaxSalary());
        jobWithCompany.setReviews(reviewList);
        jobWithCompany.setLocation(job.getLocation());

        return jobWithCompany;


    }
}
