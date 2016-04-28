package com.apakgroup.training.tutorial.webapp.examples;

import org.springframework.stereotype.Service;

@Service
public class UserBoImpl implements UserBo {

    @Override
    public String getMessage() {
        return "JSF 2 + SpringIntegration works";
    }

}
