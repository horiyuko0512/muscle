package com.example.MuscleTodo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MuscleNotFoundException extends RuntimeException{
}
