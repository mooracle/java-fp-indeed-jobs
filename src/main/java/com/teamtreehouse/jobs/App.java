package com.teamtreehouse.jobs;

import com.teamtreehouse.jobs.model.Job;
import com.teamtreehouse.jobs.service.JobService;

import java.io.IOException;
import java.util.List;

/**
 * [Entry 2: Filtering]
 *
 * First we want to filter the job listing to find if there are any that located in Portland. First we will do it
 * imperatively and then we go using declarative methods.
 *
 * We will use the static void explore method to filter it. Please note that explore method has a List of Object Job
 * called jobs. This List is where we going to filter.
 *
 * Please note in the Run result the format is the result of @Override toString method (you can check in
 * com.teamtreehouse.jobs.model.Job)
 *
 * in order to keep record of how we do it imperatively we can Refactor the code that we use to filter imperatively into
 * a provate method that will be called by the explore method.
 *
 * On the other hand declaratively the Stream package has a method called filter. The documentation link is in the
 * README.
 *
 * It has to have a predicate which takes an item and returns boolean. So the way the filter method works is if the
 * predicate returns True, it will keep the item for further processing. If False it will skip to the next item.
 *
 * The item in our case will be a Job object called job. We use lambda thus it will figure out the type of the job.
 * Since we will just going to pass one parameter (which is job) we don't need parenthesis.
 *
 * Since the lambda knows that it is a predicate it will returns boolean thus we just need to specify a boolean
 * statement which in this case that job State equals "OR" AND job City equals "Portland".
 *
 * REMEMBER filter is an intermediate method. EAch call to these intermediate methods is going to return a stream, so
 * that we can chain it together and these get pretty long pretty quick. Thus please have some manners to reserves
 * ourselves from writing the intermidate methods in one line.
 *
 * In the end all intermediate stream methods need a closure or terminal methods. In our case the process of filtering
 * is followed and closed by printing all jobs that match the filter criteria. Thus our terminal method must do that!
 * println is most suitable for Each of filtered jobs! Oh great we can just use method reference since it was println!
 *
 * WAIT A MINUTE!: The declarative way is no shorter than the imperative one. Moreover we use AND (&&) to build our
 * boolean statement imperatively. While in declarative it seems we do two works by putting two filters in the stream.
 * Is it unnecessary? Well let's take a look
 *
 * this misconception is because we look at it in imperative way. That in the process int he boolean the Java will make
 * a List of all jobs that meets criteria and short circuit those whom failed to meet the first criteria. Thus in the
 * stream the biggest misconception is we will make a list of all data that meet first filter then put that list as input
 * for the second filter method. This is WRONG.
 *
 * The way we should see a stream is it will fetch Job object called job and turns it into a marble and put it into a
 * pipe stream. Thus it will be filtered by first filter. If it failed to pass that job marble will be discarded no need
 * to run second filter.
 *
 * If the marble does pass the first filter it will not be stopped there and gathered into a list of other jobs that
 * pass the first filter. Rather that job marble will instantly passed as the input for second filter. If it failed to
 * pass second filter it will be discarded but if it pass the second filter once again it will not be gathered first,
 * instead it will instantly being treated as input forEach method.
 *
 * Moreover, by using multiple filter intermediate methods we can just comment out the filters we want to omit
 * temporarily
 *
 * Then for the last method we will Refactor the code so that it will be recoreded as a private method that we can look
 * at it at anytime to see how it is being done declaratively
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

      /*filterPortlandJobsImperatively(jobs);*/

      // filtering jobs declaratively:

      filterPortlandJobsDeclaratively(jobs);
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
