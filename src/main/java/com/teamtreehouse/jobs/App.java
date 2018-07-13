package com.teamtreehouse.jobs;

import com.teamtreehouse.jobs.model.Job;
import com.teamtreehouse.jobs.service.JobService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * [Entry 4: Transforming with Map]
 *
 * Now up to Entry 3 we have the ability to take a collection of items and gather a portion of those into another
 * collection. However, in real life we actually needing to do is collect our subset of items and then produce different
 * representations of those same items.
 *
 * For example maybe we want to populate a drop down list in an application, with limited groups of names. Maybe we
 * want to produce some mastered detail representation on our webpage, we're showing a pginated list of items as links.
 * Clicking that link will go to more detailed page.
 *
 * Stream add some real power since instead of making a separate collection of the items, we can actually transform the
 * item as it's passing through your functional pipeline.
 *
 * However, a bit of WARNING there will be a naming collision since the method that we're going to use on the stream is
 * named Map. Please do not mistaken this with the more traditional keyword for key value data structure also called map
 *
 * Similar like how the filter used the predicate functional interface, map uses the function functional interface. The
 * single abstract method is named Apply, has signature of takes single value and return a value.
 *
 * In this case we will modify how we display the jobs we search and compiled. This time we will format it to be
 * easier to read since we want to give example of certain job type. This time we will give example of Junor jobs
 * but with better format.
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
    // Your amazing code below... filtering using imperative (refactored to be a private method)

      /*[Entry 5: flatMap]
      * we will make a stream over a Strings:
      * */
      Stream.of("hello", "this", "is", "a", "stream")
              .forEach(System.out::println); // since it's a stream we can just follow up on them!
      /*[Entry 5: flatMap]
      So that is what's called static method that accepts varargs (endless amount of parameters as long as they are
      all the same type)
      */
  }

  /**
   * [Entry 4: Transforming with Map]
   *
   * This is basically similar with the getJuniorJobsImperatively so most of the component will be the same thus we
   * can just copy it from it.
   * */
  private static List<String> getCaptionImperatively(List<Job> jobs){
      List<String> captions = new ArrayList<>();

      for (Job job : jobs){
          String title = job.getTitle().toLowerCase(); // in case the title of the job is written with some capitals

          // if the title of the job contains word "junior" or "jr" then put it into captions list

          if (isJuniorJob(job)){
              String caption = job.getCaption();
              captions.add(caption);

              // if the number of jobs in junior jobs list is already three then break

              if (captions.size() >= 3){
                  break; // remember break exits if and for loops
              }
          }
      }
      return captions;
  }

  /**
   * [Entry 5: flatMap]
   *
   * Sometimes we will end up with a stream that returns a stream, flatMap helps you flatten our elements into a
   * single stream.
   *
   * In this session we need to learn how to make a stream from objects that aren't in collections. It's really
   * straightforward there is a method on stream class called OF.
   *
   * However, although we can create a stream upon a common object we will run into a common problem as well. When we
   * try to map at them and in the end it end up we are pushing a stream through a stream and it will be awkward.
   *
   * This is where a concept in programming called flattening comes to use. Flattening works if we have a List of List
   * since it is hard to work with by indexing the members of the List inside the List. We can flatten them so that
   * the members of the List inside the List is become one as member of the List.
   *
   *
   * */
  private static List<String> getCaptionStream(List<Job> jobs) {
      return jobs.stream()
              .filter(App::isJuniorJob)
              .map(Job::getCaption) // this method reference are substitute to lambda job -> job.getCaption()
              .limit(3)
              .collect(Collectors.toList());
  }

  /**
   * [Entry 3: Collecting and Limiting]
   *
   * This is how we do it imperatively:
   * -> please remember we will return a List of Job from passed List of Job objects.
   * */
    private static List<Job> getThreeJuniorJobsImperatively(List<Job> jobs){
        List<Job> juniorJobs = new ArrayList<>();

        for (Job job : jobs){
            String title = job.getTitle().toLowerCase(); // in case the title of the job is written with some capitals

            // if the title of the job contains word "junior" or "jr" then put it into juniorJobs list

            if (isJuniorJob(job)){
                juniorJobs.add(job);

                // if the number of jobs in junior jobs list is already three then break

                if (juniorJobs.size() >= 3){
                    break; // remember break exits if and for loops
                }
            }
        }
        return juniorJobs;
    }

    /**
     * [Entry 3: Collecting and Limiting]
     *
     * Now let's use stream! Instead building a new List like we did in the imperative method above when we use stream
     * they can make a new collection by themselves.
     *
     * To make this method simpler we will make additional boolean method to determine if a job is for junior or not.
     * We can just use the imperative way of doing so by using the if statement logic.
     * */
    private static List<Job> getThreeJuniorJobsStream(List<Job> jobs){
        return jobs.stream()
                .filter(App::isJuniorJob) // this is a method reference to see if isJuniorJob is True
                .limit(3) // this is intermediate method and it's lazy but its understand state of amount and can cuts
                .collect(Collectors.toList()); // this is terminal method to end it by collecting into a List
    }

    /**
     * [Entry 3: Collecting and Limiting]
     *
     * This additional method is part of the getThreeJuniorJobsStream  and getThreeJuniorJobsImperatively method above
     * This method only determines if a Job object contains word "junior" or "jr" in its title.
     * */
    private static boolean isJuniorJob(Job job){
        String title = job.getTitle().toLowerCase();
        return title.contains("junior") || title.contains("jr");
    }

  /**
   * Entry 2 Notes:
   * This method is the result of refactoring the code inside the explore method.
   * The purpose of this method is to stream Job object called job and filter it to only jobs in Portland, OR
   * This is done declaratively
   * */
    private static void filterPortlandJobsDeclaratively(List<Job> jobs) {
        jobs.stream()
                .filter(job -> job.getState().equals("OR"))
                .filter(job -> job.getCity().equals("Portland"))
                .forEach(System.out::println); //<- in the end terminal method is println each job
    }

    /**
   * Entry 2 Notes:
   * This method is the result of refactoring the code inside the explore method.
   * The purpose of this method is to filter jobs data that located in Portland, OR.
     * This is done imperatively
   * */
    private static void filterPortlandJobsImperatively(List<Job> jobs) {
        for (Job job : jobs){
            if(job.getState().equals("OR") && job.getCity().equals("Portland")){
                System.out.println(job);
            }
        }
    }
}
