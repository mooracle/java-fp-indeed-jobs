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
5. [Entry 4: Transforming with Map](https://teamtreehouse.com/library/transforming-with-map)
    1. Learn more [Stream.map JavaDoc](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#map-java.util.function.Function-)
    2. Learn more [Method References Tutorial](https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html)
6. [Entry 5: flatMap](https://teamtreehouse.com/library/flatmap)
    1. Learn more [Arguments in the official tutorial](https://docs.oracle.com/javase/tutorial/java/javaOO/arguments.html)
    search for varargs.
    2. Learn more [Regular Expressions in Java workshop](https://teamtreehouse.com/library/regular-expressions-in-java)
    3. Learn More [Regular Expression official tutorial](https://docs.oracle.com/javase/tutorial/essential/regex/pre_char_classes.html)
    4. The Imperative Word Cloud Code is in the Teacher's Notes
    
# Course: Introduction to Functional Programming - Reduction and Aggregation
1. [Entry 6: Reduction Operation](https://teamtreehouse.com/library/reduction-operations)
    1. Learn more: [Comparator JavaDoc](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html)
    2. Learn more: [IntStream JavaDoc](https://docs.oracle.com/javase/8/docs/api/java/util/stream/IntStream.html)
2. [Entry 7: Optionals](https://teamtreehouse.com/library/optionals)
    1. Learn more: [Option type (general)](https://en.wikipedia.org/wiki/Option_type)
    2. Learn more [Optional Tips and Tricks - Pro Workshop](https://teamtreehouse.com/library/optionals-tips-and-tricks)
3. [Entry 8: Ranges](https://teamtreehouse.com/library/ranges)
    1. Learn more: [Stream.distinct JavaDoc](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#distinct--)
    2. Learn more: [Stream.sorted JavaDoc](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#sorted--)
    3. Learn more: [IntStream.range JavaDoc](https://docs.oracle.com/javase/8/docs/api/java/util/stream/IntStream.html#range-int-int-)
    

## Parking Lot of this Course
Here is some terms that hasn't fully explained from the previous sub course Java Functional Toolset:

1. Pure
2. Side Effects
3. Higher Order Function: The function or method that takes another function as input and returns another function to 
be used by other function. Implementation see com/teamtreehouse/jobs/App.java
4. Method Reference Inference: The concept of method reference which uses the instance of where the method is residing
as the argument passed on in order for the method to run (see Entry 4 NOTE!)
5. Lazy
6. Functional Composition