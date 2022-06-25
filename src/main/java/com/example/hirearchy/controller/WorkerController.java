package com.example.hirearchy.controller;

import com.example.hirearchy.model.Worker;

public class WorkerController extends Worker {
    public WorkerController(String name, String contact_no, String email, int profession, String password, int location) {
        super(name, contact_no, email, profession, password, location);
    }
}
