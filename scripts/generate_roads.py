import csv
import math
import random

random.seed(50) # team parameter placeholder - swap for an actual member index number

LOCATIONS_CSV = "locations_full.csv"
OUT_CSV = "roads_full.csv"
TARGET_EDGE_COUNT = 100

def haversine_km(lon1, lat1, lon2, lat2):
    R = 6371.0
    p1, p2 = math.radians(lat1), math.radians(lat2)
    dphi = math.radians(lat2 - lat1)
    dlambda = math.radians(lon2 - lon1)
    a = math.sin(dphi / 2) ** 2 + math.cos(p1) * math.cos(p2) * math.sin(dlambda / 2) ** 2
    return 2 * R * math.asin(math.sqrt(a))

# --- load locations ---
locs = []
with open(LOCATIONS_CSV) as f:
    reader = csv.DictReader(f)
    for row in reader:
        locs.append({
            "id": row["location_id"],
            "name": row["name"],
            "type": row["location_type"],
            "lon": float(row["x_coord"]),
            "lat": float(row["y_coord"]),
        })

n = len(locs)
print(f"loaded {n} locations")

# --- full pairwise distance matrix ---
dist = [[0.0] * n for _ in range(n)]
for i in range(n):
    for j in range(n):
        if i != j:
            dist[i][j] = haversine_km(locs[i]["lon"], locs[i]["lat"], locs[j]["lon"], locs[j]["lat"])

# --- Prim's MST: guarantees every location is reachable (matches the "reachability"
# requirement in the brief) before we add any extra realism edges ---
in_tree = [False] * n
in_tree[0] = True
mst_edges = []
for _ in range(n - 1):
    best = (None, None, float("inf"))
    for i in range(n):
        if not in_tree[i]:
            continue
        for j in range(n):
            if in_tree[j]:
                continue
            if dist[i][j] < best[2]:
                best = (i, j, dist[i][j])
    i, j, d = best
    in_tree[j] = True
    mst_edges.append((i, j, d))

edge_set = set()
for i, j, d in mst_edges:
    edge_set.add((min(i, j), max(i, j)))

# --- add nearest-neighbour edges on top of the MST backbone until we hit the
# target edge count, so nearby buildings get a direct path instead of only
# a long way round through the spanning tree ---
candidates = []
for i in range(n):
    for j in range(i + 1, n):
        if (i, j) not in edge_set:
            candidates.append((dist[i][j], i, j))
candidates.sort()

for d, i, j in candidates:
    if len(edge_set) >= TARGET_EDGE_COUNT:
        break
    edge_set.add((i, j))

print(f"total edges: {len(edge_set)}")

# --- write roads CSV ---
# condition_weight: main campus internal paths are in good condition (~1.0-1.15);
# anything touching a Campus Border / Entrance node is treated as a less
# maintained access road (~1.2-1.4). Travel time assumes an average campus
# vehicle speed of 18 km/h (slower than open road because of pedestrian traffic).
AVG_SPEED_KMH = 18.0

rows = []
for idx, (i, j) in enumerate(sorted(edge_set), start=1):
    d_km = dist[i][j]
    border_involved = locs[i]["type"] == "Entrance" or locs[j]["type"] == "Entrance"
    condition = round(random.uniform(1.2, 1.4) if border_involved else random.uniform(1.0, 1.15), 2)
    time_min = round((d_km / AVG_SPEED_KMH) * 60 * condition, 1)
    rows.append([
        f"R{idx:03d}",
        locs[i]["id"],
        locs[j]["id"],
        round(d_km, 3),
        time_min,
        condition,
    ])

with open(OUT_CSV, "w", newline="") as f:
    writer = csv.writer(f)
    writer.writerow(["road_id", "from_location_id", "to_location_id", "distance_km", "travel_time_min", "condition_weight"])
    writer.writerows(rows)

print(f"wrote {len(rows)} roads to {OUT_CSV}")
