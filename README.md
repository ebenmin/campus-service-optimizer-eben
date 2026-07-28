# Ghana Smart Service Operations Optimizer — Campus Edition

DCIT 204/308 joint semester project. Models a University of Ghana (Legon) campus
service hub: locations, roads, service requests and resources are stored in a
SQLite database, loaded into custom-built data structures, and operated on by
custom-built algorithms (search, sort, graph, greedy, DP).

## Requirements

- Java 11+
- Maven 3.8+
- No IDE required, but IntelliJ/VS Code + the Java extension both work fine.

## First-time setup

```bash
git clone <your-repo-url>
cd campus-service-optimizer
mvn -q compile          # downloads dependencies and compiles
mvn -q test              # runs the unit tests
mvn -q package            # builds target/optimizer.jar
java -jar target/optimizer.jar    # runs the console menu, option 1 loads the DB
```

## Project layout

```
campus-service-optimizer/
├── schema.sql                     # database schema (run once to create tables)
├── campus.db                      # pre-built SQLite DB (regenerate anytime via menu option 1)
├── data/                          # finished seed CSVs — 50 locations, 100 roads, 300 requests, 30 resources
│   ├── locations_full.csv         # real Legon campus landmarks + coordinates (used by the app)
│   ├── roads_full.csv             # generated from real coordinates, guaranteed fully connected
│   ├── service_requests_full.csv  # script-generated, tied to team index-number parameters
│   ├── resources_full.csv         # script-generated
│   └── *_template.csv             # original empty templates, kept for reference
├── scripts/                       # Python scripts used to generate roads/requests/resources
│   ├── generate_roads.py          # builds the road network from locations' real coordinates
│   └── generate_requests_resources.py  # generates the 300 requests + 30 resources
├── docs/
│   ├── data_dictionary.md         # field-by-field description of every CSV/table
│   └── dev_log.md                 # weekly progress log (required submission item)
├── results/performance/           # exported CSV timing results + graphs go here
├── src/main/java/com/campus/optimizer/
│   ├── Main.java                  # entry point, launches the console menu
│   ├── model/                     # plain data classes (Location, Road, ServiceRequest, ...)
│   ├── db/                        # DatabaseManager (connects/creates schema) + DataLoader (CSV -> DB, fully wired)
│   ├── datastructures/            # YOUR custom structures — one file per person
│   ├── algorithms/                # search/sort/graph/greedy/DP algorithms
│   ├── performance/                # ExperimentRunner — times algorithms at increasing input sizes
│   └── ui/                        # ConsoleMenu — ties everything together for the examiner
└── src/test/java/com/campus/optimizer/datastructures/   # one JUnit test class per structure
```

## Database is already wired up

Menu option 1 in `ConsoleMenu` calls `DatabaseManager.applySchema()` then
`DataLoader.loadAll()`, which reads all four `data/*_full.csv` files and
inserts them into `campus.db`, printing the row counts loaded. This part of
the project is done — structure and algorithm owners can now write code
against real data instead of placeholders.

**Before final submission**, open `scripts/generate_requests_resources.py`
and replace `INDEX_NUMBER = 10912345` with an actual team member's index
number (the brief requires parameters derived from index numbers) — then
re-run the script to regenerate `service_requests_full.csv` and
`resources_full.csv` with that real seed, and note the formula in
`docs/data_dictionary.md`.

## How to work on your assigned structure

1. Open `src/main/java/com/campus/optimizer/datastructures/` and find your file
   (e.g. `MyStack.java` is filled in already as a worked example — read it first).
2. Each stub file has the exact method signatures required by the project brief,
   with `// TODO` markers and a javadoc comment stating the required evidence
   (e.g. "collision statistics for different load factors" for the hash table).
3. Fill in the logic. Do not use `java.util.HashMap`, `PriorityQueue`, `TreeMap`,
   `Stack`, `ArrayDeque`, or similar built-ins for your structure's core logic —
   that is explicitly disallowed for graded parts of this project.
4. Write the matching test class in `src/test/java/.../datastructures/` covering:
   a normal case, a boundary case (empty/full/single element), and an invalid
   input case. `MyStackTest.java` is filled in as a worked example.
5. Run `mvn -q test -Dtest=YourClassNameTest` to check just your class.
6. Commit and push your branch, then open a pull request into `main` so someone
   else looks at it before merging (catches bugs early, and gives you a paper
   trail of who wrote what — useful for the individual contribution statement).

## Git workflow for the team

```bash
git checkout -b feature/<your-name>-<structure-name>   # e.g. feature/kwame-hashtable
# ... make changes ...
git add .
git commit -m "Implement hash table put/get/remove with chaining"
git push -u origin feature/<your-name>-<structure-name>
# then open a pull request on GitHub into main
```

Keep commits small and frequent — the dev log and individual contribution
statement both rely on a clear commit history per person.
