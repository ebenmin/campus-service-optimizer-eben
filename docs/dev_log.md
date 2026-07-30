# Development Log

One entry per week per person (or per pair). Required submission item.

## Week 1
- Date: 2026-07-30
- Who: Chris Nana Opoku (22128192)
- What was done:
  - Set up local dev environment: installed Python 3.12 and Apache Maven
    3.9.16 (neither was present on the machine).
  - Replaced the placeholder `INDEX_NUMBER` in
    `scripts/generate_requests_resources.py` with a real team member index
    number and regenerated `service_requests_full.csv` and
    `resources_full.csv` from the new seed.
  - Implemented `src/main/java/com/campus/optimizer/IndexParams.java`,
    computing all three index-number-derived parameters the brief requires
    (random_seed, priority_weight, hash_table_size) from the full 15-member
    Group 2 "Scythe" roster, rather than hand-typing the results.
  - Updated `docs/data_dictionary.md` with the team roster and the exact
    formulas/results for all three parameters.
- Challenges:
  - The dev machine only had non-functional Windows Store app-execution
    aliases for `python`/`mvn`; had to install both properly via winget.
  - The brief's literal `hash_table_size` formula ("nearest prime to average
    team index number") produces ~22.26 million, which is not a usable
    bucket-array size.
- Decisions made:
  - `random_seed` and `priority_weight` both derive from one confirmed
    member's index number (22128192); `hash_table_size` derives from the
    full team average, scaled down (`mod 1000`) before finding the nearest
    prime, so the formula stays traceable to real index numbers while
    staying practical (result: 409). Documented in the data dictionary so
    the scaling choice isn't lost.

## Week 2
...

## Week 3
...

## Week 4
...
