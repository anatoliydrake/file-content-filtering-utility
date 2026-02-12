# File Content Filtering Utility

A command-line utility for filtering text file contents by data type (integers, floats, strings).

## Requirements

- **Java Version:** 21
- **Build System:** Apache Maven 3.6.0+

**Verify Java installation:**
```bash
java -version
```
Should show version 21 or higher.

## Third-Party Libraries

| Library | Version | Scope | Reference |
|---------|---------|-------|-----------|
| Lombok | 1.18.42 | provided | https://projectlombok.org/ |
| JUnit Jupiter | 5.10.1 | test | https://junit.org/junit5/ |

## Building

```bash
mvn clean package
```

This creates an executable JAR with dependencies:
```
target/file-filter-0.0.1-jar-with-dependencies.jar
```

## Running

### Basic Usage

```bash
java -jar target/file-filter-0.0.1-jar-with-dependencies.jar <input-files>
```

### Command-Line Options

| Option | Description |
|--------|-------------|
| `-o <path>` | Output directory for result files |
| `-p <prefix>` | Prefix for output file names |
| `-a` | Append mode (append to existing files instead of overwriting) |
| `-s` | Print short statistics (counts only) |
| `-f` | Print full statistics (min, max, sum, average) |

### Examples

**Filter a single file:**
```bash
java -jar target/file-filter-0.0.1-jar-with-dependencies.jar input.txt
```

**Filter multiple files with statistics:**
```bash
java -jar target/file-filter-0.0.1-jar-with-dependencies.jar -f in1.txt in2.txt
```

**Use output directory and prefix:**
```bash
java -jar target/file-filter-0.0.1-jar-with-dependencies.jar -o output -p sample- input.txt
```

**Append to existing files with short statistics:**
```bash
java -jar target/file-filter-0.0.1-jar-with-dependencies.jar -a -s input.txt
```

### Complete Example

**Input file: in1.txt**
```
Lorem ipsum dolor sit amet
45
Пример
3.1415
consectetur adipiscing
-0.001
тестовое задание
100500
```

**Input file: in2.txt**
```
Нормальная форма числа с плавающей запятой
1.528535047E-25
Long
1234567890123456789
```

**Command:**
```bash
java -jar target/file-filter-0.0.1-jar-with-dependencies.jar -s -a -p sample- in1.txt in2.txt
```

**Output file: sample-integers.txt**
```
45
100500
1234567890123456789
```

**Output file: sample-floats.txt**
```
3.1415
-0.001
1.528535047E-25
```

**Output file: sample-strings.txt**
```
Lorem ipsum dolor sit amet
Пример
consectetur adipiscing
тестовое задание
Нормальная форма числа с плавающей запятой
Long
```

**Console output (with -s flag):**
```
Integer statistics:
  Count: 3
Float statistics:
  Count: 3
String statistics:
  Count: 6
```

## Output Files

The utility creates separate output files based on data type:

- `integers.txt` - Integer values
- `floats.txt` - Floating-point values (including scientific notation)
- `strings.txt` - String values

Files are created only if data of that type exists in the input.

## Running Tests

```bash
mvn test
```
