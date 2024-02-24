# Module 01: Coding Standards

Feru Pratama Kartajaya<br>
2106750351

## Reflection 1

```
You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding
standards that you have learned in this module. Write clean code principles and secure coding practices that have been
applied to your code. If you find any mistake in your source code, please explain how to improve your code.
```

- Meaningful Names<br>
  In my code, I always attempt to give descriptive names to variables and methods.
- Functions<br>
  Functions are made to be small and only do one thing. This can be seen in the separation of different methods 
  relating to Product repository and controller.
- Comments<br>
  I attempt to avoid unnecessary comments altogether by using variable names and instantiation, as well as descriptive
  code writing, to write code that is both readable and easy to understand.

## Reflection 2

```
1. After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that 
our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is 
a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean 
your code has no bugs or errors?

2. Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were 
asked to create another functional test suite that verifies the number of items in the product list. You decided to 
create a new Java class similar to the prior functional test suites with the same setup procedures and instance
variables.

What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code 
quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the 
code cleaner!
```

1. After writing the unit tests, I feel more confident in the correctness and quality maintenance of my code. I think 
   that the amount of unit tests that should be made is as much as it is necessary to verify all functions of the code. 
   Test cases that are redundant and do unnecessary checks may reduce overall code quality.
   <br>
   100% code coverage only means that every aspect of your code is covered and is able to be tested by unit tests. It 
   does not mean that there are no mistakes in the code, or that the test suites that cover said code are of good 
   quality.
   

2. In terms of clean code principles, the main problem of creating similar functional test suites is that it introduces 
   a lot of code duplication. Multiple test suites will share the same lines of code which adds redundancy. A possible 
   improvement to be made is to combine these test suites into one larger test suite. This prevents any duplicate code 
   to be shared between test suites. However, the context of each test suite should be put into consideration if you 
   were to do this. While the setup procedures and instances between test suites might be similar, they might be 
   testing very different functionalities. Combining these suites into one might create clutter within the code and be 
   very hard to read and understand.

# Module 02: CI/CD & DevOps

Feru Pratama Kartajaya<br>
2106750351

Deployed App Link: https://advprog2324-ferupk.koyeb.app/

## Reflection

```
1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the 
definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!
```

1. A code quality issue that was found by PMD was located in the Product service interface. Prior to scanning the code, 
   the interface methods had the public modifier. However, PMD warned me about this and suggested that these explicit
   modifiers be removed. The reasoning given is that interface methods are set to public by default. Therefore, adding 
   the public modifier to these methods could be seen as redundant. I've decided that I agree with the scanner's
   assessment and have since removed these modifiers.
   <br>
   There was another quality issue that PMD brought up in the Product controller. It suggested that I remove an unused
   import on Line 8. This seems to be a false warning, as it refers to the import for Spring Web's Annotation package.
   This package has several uses within the controller's code, such as mapping methods to a URL or binding a method's
   parameters to a model object. Therefore, this issue was ignored.


2. I believe that the workflows that have been implemented have satisfied the definitions of CI/CD due to these reasons:
   - The codebase is supported by unit tests that are managed with the Gradle tool.
   - The codebase is scanned for possible issues regarding security and code quality by OSSF Scorecard and PMD.
   - The codebase is built and deployed to a production environment utilizing Koyeb PaaS.
   - All tasks mentioned above are automated upon codebase update and run on GitHub Actions workflows.

# Module 03: Maintainability & OO Principles

Feru Pratama Kartajaya<br>
2106750351

Deployed App Link: https://advprog2324-ferupk.koyeb.app/

## Reflection

```
1. Explain what principles you apply to your project!

2. Explain the advantages of applying SOLID principles to your project with examples.

3. Explain the disadvantages of not applying SOLID principles to your project with examples.
```

1. SOLID principles that have been implemented in the project are as follows:
   - Single Responsibility Principle? Fixed. Classes in the codebase have already been separated according to their
     responsibility in the Spring Boot application architecture (Model, Repository, Service, Controller). Furthermore,
     the classes for managing Product and Car interactions have also been split into separate classes. This arrangement
     results in 2 Model classes, 2 Repository classes, 2 Service interfaces and class implementations, and 2 Controller
     classes.

     However, there is one issue that violates SRP. In the previous code, the CarController class is defined as an 
     extension of the ProductController class. However, this would imply that the CarController class can have 2 
     responsibilities, which are managing Product interactions and managing Car interactions. This clearly violates 
     SRP and must be corrected by removing the inheritance relationship between ProductController and CarController. I 
     have also refactored the two classes into separate .java files.

   - Open-Closed Principle? Fixed. Both Product and Car repositories feature methods for editing an object in a 
     repository. In both instances, editing is done by getting a desired object and setting said object's attributes
     manually. This implementation violates OCP as the method for editing an object has to be modified anytime there is 
     a change impacting the object's list of attributes. An example of such a case would be a new class that
     extends the previous object but with a new set of unique attributes.

     This violation is solved by instead removing the old object from the repository and inserting a new edited object 
     in its original place. The method is no longer tied to the object's attributes and does not need any modification 
     if the list of attributes changes.

   - Liskov Substitution Principle? Already implemented. In the previous code, the CarController class is defined as an 
     extension of the ProductController class. Therefore, CarController can be used to handle requests of both Product 
     and Car interactions. Furthermore, there are no cases of overriding a ProductController method in the CarController
     class, so calling a ProductController method from the CarController class will return the same results as if the 
     method was called from the ProductController class. 

     In the current code, this principle is also not an issue as the inheritance relationship between ProductController 
     and CarController is gone as a result of implementing SRP.

   - Interface Segregation Principle? Already implemented. The codebase has 2 interfaces, ProductService and CarService.
     Each interface is designed to be used specifically for the implementation of each respective services. Even though 
     these interfaces contain a wide variety of methods, I believe that grouping these methods into a single interface
     is justified as the methods are integral to any service implementation.

   - Dependency Inversion Principle? Fixed. In the previous code, the CarController class is autowired to an instance of
     the CarServiceImpl class to process Car interactions. This violates DIP as the implementation of CarController is
     directly coupled with that specific implementation of the CarService interface. To solve this violation, we simply
     substitute the CarServiceImpl class with the CarService interface. Now CarController is not dependent on one
     implementation of CarService.

2. The advantages of applying SOLID principles in the project are as follows:
   - Code modularity and decoupling. Adhering to SOLID principles reduces the amount of dependencies between sections of
     code. This greatly reduces the risk of errors appearing all over the codebase when editing a section of code, as
     that section's implementation won't directly affect other parts of code. This also means that no other code needs
     to be modified except for the part that is being changed. An example is the use of the CarService interface in the
     CarController class. Relying on an Interface allows CarController to use any implementation class of CarService.
   - Code maintainability. Adhering to SOLID principles results in code that is neatly structured and easier to
     comprehend. Each section of code should be easy to modify and understand its purpose. An example is the Spring Boot
     structure that is used in this project. Each piece of code is written for a specific purpose (model, repository,
     service, controller) and clearly intended for different intentions (managing Product or Car interactions).
   - Testability. Adhering to SOLID principles results in compact pieces of code that can be separately tested and verified
     with unit tests. Tests can be designed to be bite-sized and test specific bits of code without having to worry about
     other sections.

3. The disadvantages of not applying SOLID principles in the project are as follows:
   - Heavy coupling. Not adhering to SOLID principles could result in code that is heavily dependent on one another.
     Editing a section of code has the potential to alter the implementation of another section, which means that said
     section must also be modified. An example would be the implementation of object editing before applying SOLID
     principles. The past implementation is highly dependent on an objects set of attributes, and any change in that set
     would necessitate change in the editing implementation as well.