For each Principle of Universal Design, write 2-3 sentences — or point form notes — explaining which features your program adhere to that principle. If you do not have any such features, you can either:

Single responsibility principle (SRP)

Each part of our code is split into many different classes, each class ensuring each class has only one responsibility
We did this by splitting the login, adding ingredients, and filters, randomizing, and displaying instructions into their own package

Open/closed principle (OCP)

Inside the use case, the logic is closed for modification but open for extension.
The API call is open to extension by allowing new methods and classes to be implemented for calling the API in different ways, while modification to the existing code is not required.
This allows our program to be open for future improvements and updates

Liskov substitution principle (LSP)

The use case layers depend on interfaces for external use (OutputBoundary and InputBoundary).
This also ensures that inner classes do not call outer classes
This allows the substitution of one implementation of an interface with another without breaking the program.

Interface segregation principle (ISP)

Our code uses small, specific interfaces rather than large, general ones. Such as splitting the code into many parts (DataAccess, Controller, Interactor, View)
This aligns with ISP by ensuring that each interface provides just the methods needed

Dependency inversion principle (DIP)

The program adheres to the DIP by following Clean Architecture by ensuring that high-level classes do not depend on lower-level classes (Use case and Data Access)




Write a paragraph (3-6 sentences) about who you would market your program towards, if you were to sell or license your program to customers. This could be a specific category of users, such as "students", or more vague, such as "people who like games". Try to give a bit more detail along with the category.

If we were to market our program, we would target home cooks and people who enjoy cooking, allowing them to discover and organize recipes tailored to their dietary restrictions and the ingredients they have. This includes people who try to reach specific nutrition such as vegan or vegetarian or who are managing intolerances and may find it hard to find recipes. Additionally, the program targets individuals who are too busy so they can find a quick and easy recipe to follow to save time in the kitchen. By incorporating, filters, ingredient searching, and step-to-step instructions, this project offers a valuable program for anyone looking to increase their cooking capabilities

Write a paragraph about whether or not your program is less likely to be used by certain demographics. Your discussion here should be informed by the content of our embedded ethics modules this term.

This program may be less likely to be used by the elderly who may lack the skills to use technology or struggle navigating the interface. They may face challenges with reading small text, complex menus, or features that require digital literacy. Furthermore, the program does not include a text-to-speech function which could drive away users who are visually impaired or those who find it easier to listen to instructions rather than read them. Without these features, the program may alienate a demographic that could benefit from a more accessible, user-friendly recipe app. Addressing these disparities by incorporating easier navigation, larger text options, and text-to-speech features can make the program more accessible to everyone.
