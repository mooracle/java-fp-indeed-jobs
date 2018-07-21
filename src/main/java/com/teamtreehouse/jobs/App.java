package com.teamtreehouse.jobs;

import com.teamtreehouse.jobs.model.Job;
import com.teamtreehouse.jobs.service.JobService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * [Entry 9: Infinite Stream]
 * in the standard ranges we use standard increment that represented imperatively as i++ which increment by 1 point.
 * However we can use more than 1 as increment and it's called as stepping.
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

      /*[Entry 10: Closures]
      * This is where all functions from previous session replaced with one functions that call the method
      * createDateStringConverter.
      *
      * Okay there is a big Note here: in the method createDateStringConverter we are using the inFormatter and
      * outFormatter which is an object of DateTimeFormatter which should be destroyed when it leaves the scope of the
      * class. However, it still available later if there is an apply method needed them alter on. Remember this
      * functions are lazy and it needs to be called out specifically. In the mean time they will be stored in what is
      * called lexical scope not in the original method scope. This is what is called as closure.
      *
      * WARNING it only limited to returned function and all attributes that comes with it and it must be final. If in
      * the method outside returned function we add another code that will give side effect of changing anything inside
      * the returned function on the fly it will be rejected.
      *
      * Why would we create this kind of cumbersome method with it pure rules? Well it helps to create configured
      * functions that can be stored in lexical scope and be called at anytime we needed them. Since these functions are
      * lazy it will be faster and more efficient to prepare them before hand and store them in lexical storage
      * */
      Function<String,String> converter = createDateStringConverter(
              DateTimeFormatter.RFC_1123_DATE_TIME,
              DateTimeFormatter.ISO_DATE
      );

      jobs.stream()
              .map(Job::getDateTimeString)
              .map(converter)
              .limit(5)
              .forEach(System.out::println);
  }

  /**
   * [Entry 10: Closures]
   *
   * So we already able to create higher order function by passing a function as an argument. Another way to create
   * one of this higher order function is to return a function from a method just like a commonly value.
   *
   * This approach allows us to produce and control functions from outside
   *
   * In this case we will continue the project of last parking slot higher function. Thus we want to take any date String
   * process it and we produce another date String as return but we want to create that function on the fly not like
   * previous session as an object in the exlplore method
   *
   * We still will use the date time formatter to helps us out. Let's make that method which will return a Function of
   * String as an input and String as an output. Please note we will return the Function not the String of Date and Time
   *
   * This basically replaces the Functions used in the explore methods.
   * */
  public static Function<String, String> createDateStringConverter(
          DateTimeFormatter inFormatter,
          DateTimeFormatter outFormatter){
      return dateString -> {
          /*[Entry 10: Closures]
          * This lambda function basically uses LocalDateTime.parse method to switch the String Date time input to
          * LocalDateTime type object. Then it will re-format it into whatever format it wishes to use.
          * This will replace all the functions in the explore method*/
          return LocalDateTime.parse(dateString, inFormatter)
                  .format(outFormatter);
      };
  }

  /**
   * Parking slot
   * This method is needed for the usage of function composition
   * If the condition determined by the Predicate<Job> checker is TRUE then it will send email
   * */
  public static void emailIfMatches(Job job, Predicate<Job> checker){
      if (checker.test(job)){
          System.out.println("I am sending an email about: " + job);
      }
  }

    private static void displayCompaniesMenuRange(List<String> companies) {
        /*[Entry 8: Ranges]
        * As for the declarative way we follow the same principles but we are now using what is called range.
        * */
        IntStream.range(0, 20) //<- range means 0 < i < 20; while rangeClose will make it 0 < i <= 20
                .mapToObj(i -> String.format("%d. %s", // same as mapToInt we can reverse int back to object (job)
                        i + 1, companies.get(i)))
        .forEach(System.out::println);
    }

    private static void displayCompaniesMenuImperatively(List<String> companies) {
        /*[Entry 8: Ranges]
        * After we make a List of all companies in the List we will make the menu for the users to choose from.
        * we will make a list of let say 20 companies.
        *
        * We do this imperatively first
        * */
        for (int i = 0; i < 20; i++) {
            System.out.printf("%d. %s %n", i + 1, companies.get(i));
        }
    }

    /**
   * Entry 7: Optionals]
   * This is the method used for finding the lucky Search style for the jobs
   * */
    private static Optional<Job> luckySearchJob(List<Job> jobs, String searchTerm) {
        return jobs.stream() //watch this is where Optional is used
                  .filter(job -> job.getTitle().contains(searchTerm))
                  .findFirst();
    }

    /**
   * [Entry 5: flatMap]
   *
   * Now we will do get Snippet word count using stream
   *
   * remember we need to return a map amd the snippet is a sentence thus we need regex
   *
   * Therefore we will get 2 streams : 1. stream of jobs and 2 stream of words from the snippet. We need to flatten
   * this out!
   *
   * So this is how this works. First we need to stream each job from List of Jobs. Then from each job we want to map
   * job with its snippet.
   *
   * Then we goint to map again each job with words from it snippet (we split them using regex)
   *
   * Then we flatten it using flatMap which will create a stream of words (each word from a words)
   *
   * We must filter to ensure it it a word by measuring the length to be > 0
   *
   * Then if it is a word we will map each String word to the lowercase version of that word.
   *
   * After that we collect is as BiConsumer since we need to pair it as word and the counter of the word.
   *
   * NOTE:
   * Here we use Function.identity to represent word that weill be counted using Collectors.counting. Plus we also use
   * Collectors.groupingBy method to pair each word with the counter results. The Function.identity method is represents
   * each word so that it does not have to use Lambda function word -> word which just an empty function that passed
   * word and returns word.
   * */
  public static Map<String, Long> getSnippetWordCountsStream(List<Job> jobs){
      return jobs.stream()
              .map(Job::getSnippet)
              .map(snippet -> snippet.split("\\W+"))
              .flatMap(Stream::of) // this method reference subs Lambda: words -> Stream.of(words)
              .filter(word -> word.length() >0)
              .map(String::toLowerCase)
              .collect(Collectors.groupingBy(
                      Function.identity(), // this is the identity function
                      Collectors.counting()
              ));
  }

  /**
   * [Entry 5: flatMap]
   *
   * This is where we want to make Map to gather cloud data on how many times a words in the snippet is used.
   * This time imperatively.
   *
   * Since Snippet is a long sentence we need to split it into words using regular expression.
   *
   * Then after that we can map certain String (words and how many time it came up.
   * */
  public static Map<String, Long> getSnippetWordCountImperatively(List<Job> jobs){
      Map<String, Long> wordCounts = new HashMap<>();

      for (Job job : jobs){
          String[] words = job.getSnippet().split("\\W+");
          for (String word : words){
              if (word.length() == 0){
                  continue;
              }
              String lWord = word.toLowerCase();
              Long count = wordCounts.get(lWord);
              if (count == null){
                  count = 0L;
              }
              wordCounts.put(lWord, ++count); // ++ count because it is pre-increment (it add the values before
          }
      }
      return wordCounts;
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
