package com.teamtreehouse.jobs;

import com.teamtreehouse.jobs.model.Job;
import com.teamtreehouse.jobs.service.JobService;

import java.io.IOException;
import java.util.List;

/**
 * [ENTRY 1: Understanding Streams and Setting Up The Project]
 *
 * Streams is a sequence of elements like a Collection but very different. Collections are data structure that store
 * elements, while Stream carries values from a source through a set of operations. These operations form a pipeline
 * that allows you to express common manipulation on those values in declarative fashion.
 *
 * There are 3 important concept we need to learn briefly here:
 *  - Streams are lazy:
 *                      They don't do anything until you tell them to. More on this concept are the understanding of
 *                      intermediate operation and terminal operation. Intermediate operation is the bits (processes) in
 *                      the middle leading to terminal Operation.
 *                      While terminal operation is the final steps that define the reason why all of those operations
 *                      happened.
 *
 *                      Streams work a lot like this, you will create a stream first from some data. You add a function
 *                      to its pipeline through some specific methods that are exposed and after you use one of those
 *                      methods and append your function the method will return the stream (back as stream) and you can
 *                      have another intermediate function appended to the pipeline.
 *
 *                      The stream doesn't do anything until you tell it to. You tell it to do something by adding a
 *                      terminal operation, but until you add that operation your stream will be lazy.
 *
 *  - Streams are declarative:
 *                      We don't worry about Iterations and flows here down the stream. Streams solved problems that you
 *                      have solved in an imperative fashion. However, using declarative fashion it will be more
 *                      succinct and legible since it removes a lot of ceremonies we have become so familiar with. They
 *                      will make heavy use of those functional shapes we just explored in Java Functional Toolset.
 *
 *  - Streams can run in parallel:
 *                      Because Streams are declared, streams can run in parallel. However, it must follow a strict
 *                      rules of functional programming rules. Streams can vewy easily be made prallel through a simple
 *                      option. it can make five of the exact same streams and run your data through the pipeline. Then
 *                      when they all completed combine the results. This running multiple processes or mini programs is
 *                      called Concurrency (link in the README) can be done imperatively but it will be more challenging.
 *
 *                      Note it will only works if you follow some functional programming principles. Your functions
 *                      need to be pure, not causing side effects.
 *
 *  To set a project we need to verify the cached data can be read and add some printed message. The project description
 *  is provided in the README.
 * */
public class App {

  public static void main(String[] args) {
    JobService service = new JobService();
    boolean shouldRefresh = false;
    try {
      if (shouldRefresh) {
        service.refresh();
      }
      List<Job> jobs = service.loadJobs();
      System.out.printf("Total jobs:  %d %n %n", jobs.size());
      explore(jobs);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void explore(List<Job> jobs) {
    // Your amazing code below... first testing using a message

      System.out.println("Test the cache");

  }
}
