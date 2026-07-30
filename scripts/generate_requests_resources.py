import csv
import random
from datetime import datetime, timedelta

# ---------------------------------------------------------------------------
# TEAM PARAMETERS — replace these with real values derived from member index
# numbers before final submission (the brief requires this explicitly).
# Example formula used here: take one member's index number, e.g. 10912345,
# SEED = last 4 digits = 2345. Document whichever formula you actually use in
# docs/data_dictionary.md under "Index-number-derived parameters".
# ---------------------------------------------------------------------------
INDEX_NUMBER = 22128192          # <-- real member index number
SEED = INDEX_NUMBER % 10000      # -> 8192
NUM_REQUESTS = 300
NUM_RESOURCES = 30

random.seed(SEED)

LOCATIONS_CSV = "locations_full.csv"
REQUESTS_OUT = "service_requests_full.csv"
RESOURCES_OUT = "resources_full.csv"

CATEGORIES = ["Medical", "IT Support", "Maintenance", "Security", "Shuttle", "Document"]
# urgency skewed toward low/medium, a smaller tail of high-urgency (5) requests,
# similar to a real dispatch load
URGENCY_WEIGHTS = {1: 25, 2: 25, 3: 25, 4: 15, 5: 10}

RESOURCE_TYPES = ["Shuttle Van", "Security Patrol Car", "Maintenance Cart", "IT Technician", "Security Officer"]

# --- load locations ---
locs = []
with open(LOCATIONS_CSV) as f:
    for row in csv.DictReader(f):
        locs.append(row["location_id"])

def weighted_urgency():
    choices, weights = zip(*URGENCY_WEIGHTS.items())
    return random.choices(choices, weights=weights, k=1)[0]

def random_timestamp(base_date, day_span=14):
    day_offset = random.randint(0, day_span - 1)
    hour = random.randint(6, 21)  # campus operating hours
    minute = random.choice([0, 5, 10, 15, 20, 30, 45])
    dt = base_date + timedelta(days=day_offset, hours=hour, minutes=minute)
    return dt

# deadline SLA (minutes) depends on urgency: more urgent -> tighter deadline
SLA_MINUTES = {1: 240, 2: 150, 3: 90, 4: 45, 5: 20}

STATUSES = ["NEW", "IN_PROGRESS", "DONE", "CANCELLED"]
STATUS_WEIGHTS = [10, 15, 70, 5]

# --- service requests ---
base_date = datetime(2026, 7, 1, 0, 0)
rows = []
for i in range(1, NUM_REQUESTS + 1):
    source, dest = random.sample(locs, 2)
    category = random.choice(CATEGORIES)
    urgency = weighted_urgency()
    submitted = random_timestamp(base_date)
    deadline = submitted + timedelta(minutes=SLA_MINUTES[urgency])
    status = random.choices(STATUSES, weights=STATUS_WEIGHTS, k=1)[0]
    rows.append([
        f"Q{i:03d}", source, dest, category, urgency,
        submitted.strftime("%Y-%m-%dT%H:%M"),
        deadline.strftime("%Y-%m-%dT%H:%M"),
        status,
    ])

with open(REQUESTS_OUT, "w", newline="") as f:
    w = csv.writer(f)
    w.writerow(["request_id", "source_location_id", "destination_location_id", "category",
                "urgency", "time_submitted", "deadline", "status"])
    w.writerows(rows)
print(f"wrote {len(rows)} service requests to {REQUESTS_OUT}")

# --- resources ---
res_rows = []
for i in range(1, NUM_RESOURCES + 1):
    rtype = random.choice(RESOURCE_TYPES)
    home = random.choice(locs)
    capacity = {
        "Shuttle Van": random.randint(8, 14),
        "Security Patrol Car": random.randint(2, 4),
        "Maintenance Cart": random.randint(1, 3),
        "IT Technician": 1,
        "Security Officer": 1,
    }[rtype]
    availability = random.choices(["AVAILABLE", "BUSY", "OFFLINE"], weights=[70, 25, 5], k=1)[0]
    prefix = {"Shuttle Van": "V", "Security Patrol Car": "P", "Maintenance Cart": "M",
              "IT Technician": "T", "Security Officer": "S"}[rtype]
    res_rows.append([f"{prefix}{i:03d}", rtype, home, capacity, availability])

with open(RESOURCES_OUT, "w", newline="") as f:
    w = csv.writer(f)
    w.writerow(["resource_id", "resource_type", "home_location_id", "capacity", "availability_status"])
    w.writerows(res_rows)
print(f"wrote {len(res_rows)} resources to {RESOURCES_OUT}")
