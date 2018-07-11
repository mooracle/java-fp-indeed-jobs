# Indeed Jobs

This repository accompanies the [Treehouse](https://teamtreehouse.com) course [Introduction to Functional Programming](https://teamtreehouse.com/library/introduction-to-functional-programming/upcoming).

This codebase is used to explore Java 8 functional programming concepts.  It uses data from the job aggregator [Indeed](http://indeed.com).

Current data is cached and included in the repository.


### To refresh the job data
Sign up to be an [Indeed publisher](https://www.indeed.com/publisher).  Add your publisher key to the [config.properties](src/main/resources/config.properties) file,
and set `shouldRefresh` in [App.java](src/main/java/com/teamtreehouse/jobs/App.java#L13).

#### Using this code
You can jump to a certain point in the course by checking out a tag.  Tags are in the format of `sXvY` where,
`X` is the stage number and `Y` is the video number.  For instance to get your code set to the 4th video in stage 1,
you would perform the following git command:

`git checkout s1v4`

# Course: Introduction to Functional Programming - Meet Streams
Let's explore the Streams API which provides powerful declarative ways to process sequences of elements.

## Teacher's Notes
1. [ENTRY 1: Understanding Streams](https://teamtreehouse.com/library/understanding-streams)
    1. [Cocurency in the Java Tutorial](https://docs.oracle.com/javase/tutorial/essential/concurrency/)
    2. [Pee-wee Herman's Rube Goldberg Machine](https://www.youtube.com/watch?v=KVdqwD_bcPs)
    3. [OK GO Rube Goldberg Machine](https://www.youtube.com/watch?v=qybUFnY7Y8w)
2. [ENTRY 1: Understanding Streams and Setting Up Project](https://teamtreehouse.com/library/setting-up-the-project-4)
    1. [Original Indeed Jobs GitHub Repo](https://github.com/treehouse-projects/java-fp-indeed-jobs)
    2. [Learn more on Indeed Job Aggregator](http://www.indeed.com/)
3. [Entry 2: Filtering](https://teamtreehouse.com/library/filtering)
    1.[Stream.filter Java Doc](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#filter-java.util.function.Predicate-)
4. [Entry 3: Collecting and Limiting](https://teamtreehouse.com/library/collecting-and-limiting)
    1. Learn More: [Stream.limit Java Doc](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#limit-long-)
    2. Learn More: [Stream Operation and Pipelines](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html#StreamOps)
    3. Intermediate operation are further divided into stateless and stateful operations. 
    4. Stateless operations, such as filter and map, retain no state from previously seen element when processing a new 
    element. Each element can be processed independently of operations on other elements.
    5. Stateful operations, such as distinct and sorted, may incorporate state from previously seen elements when 
    processing new elements.

## Parking Lot of this Course
Here is some terms that hasn't fully explained from the previous sub course Java Functional Toolset:

1. Pure
2. Side Effects
3. Higher Order Function
4. Method Reference Inference
5. Lazy
6. Functional Composition