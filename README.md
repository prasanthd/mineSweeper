# Minesweeper
### Tools: 
Java, Gradle, Junit

### Setup dependencies:
Linux machine, Java

### How to run?
#### Option1: Running using gradle
1. Install Java and gradle
2. RUN command ```gradle build```
3. Execute this command ```gradle run --console=plain```
4. Application will start in console
#### Option2: Running using IDE
1. Go to Main.java and Run using IDE

### Design
1. Segregated Domain class and command line input class, Domain class can be reused for any user interface
2. Commandline input validations are covered for limited cases
3. Validations are done for limited cases. Validator class can be referred to understand the validation pattern used in the project
4. MineFieldTest file covers the extensive test cases
5. KISS, SOLID, YAGNI principles are followed 
6. Domain class are written through TDD with Junit tests




