# HotStone

HotStone is a Hearthstone-inspired card game built in Java as the mandatory
project for **Software Engineering and Architecture (SWEA)**, a course in the
Data Science bachelor programme at Aarhus University. The project was
developed test-first across a series of iterations, and is shared here as a
demonstration of test-driven development (TDD) and test automation practice.

The original course framework and starting point for this project were
provided by Henrik Bærbak Christensen (Aarhus University, Computer Science).

## Collaboration & Authorship

This project was built through pair programming with **Bjarke Kjær** as part
of the course. We worked together at a shared machine and alternated writing
code, so the git commit history reflects whose machine/account was active
during a given session rather than sole individual authorship — both of us
contributed across all iterations of design, implementation, and testing.

## Tech Stack

- **Java**, built and tested with **Gradle** (wrapper pinned to 8.1.1)
- **JUnit 5** (Jupiter) for unit testing, with **Hamcrest** matchers
- **JaCoCo** for code coverage reporting
- **FRDS.Broker** for the client-server / distributed variant of the game
- **MiniDraw** for the graphical (2D direct-manipulation) interface

## A Note on a Missing Dependency

One deck variant, `PersonalizedDeckStrategy` (and its game factory and test),
originally depended on a course-internal library, `hotstone-deck`, that was
only ever published to a Sonatype OSSRH snapshot repository. That service was
sunset by Sonatype in mid-2025 and the artifact is no longer publicly
resolvable. Those three files have been excluded from the build accordingly.
Every other test in the project builds and runs normally — this exclusion is
isolated to that single, course-internal deck variant.

## Setup & Build Instructions

**Prerequisites:** a JDK (11 or 17 both work fine; the project doesn't pin a
specific version).

### Option A — IntelliJ IDEA

1. Choose **Open** and select the `build.gradle` file inside the project
   folder (not just the folder itself) — this imports it as a Gradle project.
2. Make sure a JDK is configured under **File > Project Structure > Project**.
3. Let Gradle sync (Gradle tool window, right edge of the IDE).
4. Run tests via the Gradle tool window (`hotstone > Tasks > verification >
   test`), or right-click `src/test/java` and choose **Run All Tests**.

### Option B — Command line

From the project root:

```bash
# macOS / Linux / Git Bash
./gradlew test

# Windows cmd.exe
gradlew test

# Windows PowerShell
.\gradlew test
```

Test results print to the console; a full HTML report is written to
`build/reports/tests/test/index.html`, and JaCoCo coverage reports to
`build/reports/jacoco`.

## Testing Approach

This project was built test-first (TDD), using a mix of techniques
appropriate to different layers of the system:

- **Unit tests** (JUnit 5 + Hamcrest) drive the core game rules, developed
  incrementally across iterations.
- **Test doubles** — stubs (`StubCard`, `StubHero`) and a fake
  (`FakeObjectGame`) isolate units under test from their collaborators.
- **Observer tests** verify game-state changes correctly notify registered
  observers.
- **Transcript testing** captures a log of interactions to assert on a
  sequence of calls, without a full mocking framework.
- **Broker/proxy integration tests** exercise the full client-server
  architecture (marshalling, proxies, invokers) using an in-process request
  handler double, so the distributed layer is tested without a live server.
- **Manual/exploratory GUI tests** visually test-drive the MiniDraw-based
  interface for behavior that's impractical to assert on automatically.
- **JaCoCo** is integrated into the Gradle build for code coverage reporting.

## Project Structure

```
src/main/java/hotstone/   Production code (game logic, GUI, broker layer)
src/test/java/hotstone/   Test suite (unit, observer, transcript, broker,
                           and manual GUI test-drivers)
build.gradle              Build configuration, dependencies, test setup
gradlew, gradlew.bat       Gradle wrapper — no local Gradle install needed
```
