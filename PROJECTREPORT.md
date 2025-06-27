# 🧾 Project Tracker API - Performance Profiling Report

## 📌 Initial Profiling Summary

**Tools Used:**
- JMeter (Load & Stress Testing)
- VisualVM (JVM Monitoring & Heap Analysis)
- Spring Actuator (Health and Metrics Endpoint)

**Test Scenarios:**
- High-volume task creation
- Concurrent developer and project queries
- Frequent status-filtered task retrieval
- Bulk assignment of tasks to developers

**Metrics Captured:**
- Average Response Time
- Throughput (Requests/sec)
- Memory Consumption
- GC Activity
- CPU Utilization

---

## ⚠️ Performance Issues Discovered

| Area | Description | Impact |
|------|-------------|--------|
| **Task Filtering Endpoint** | Unindexed DB filtering on status | Slower response under load |
| **DTO Conversion** | Deep object mapping without projection | Increased memory usage |
| **Database Writes** | Lack of batching on inserts | High response latency |
| **Redundant Calls** | Repeated repo queries in service logic | Increased DB load |

---

## ✅ Optimization Actions and Their Impact

| Optimization | Description | Result |
|--------------|-------------|--------|
| **@Cacheable Tasks by Status** | Cached filtered tasks for repeat queries | ~60% faster response on second hit |
| **Pagination & Sorting** | Introduced limit + order by in queries | Reduced memory pressure and faster responses |
| **DTO Refactor** | Moved to lightweight DTOs via mapstruct/util | Lowered heap usage in high-load tests |
| **DB Index on `status` column** | Added index on `tasks.status` | Filter time dropped from ~400ms to ~20ms |
| **@Transactional Operations** | Batch deletion with cascading | Reduced DB calls and improved consistency |

---

## 📊 Before vs. After Benchmarks

| Scenario | Before (avg) | After (avg) | Δ Improvement |
|----------|--------------|-------------|----------------|
| Get tasks by status (1000 tasks) | 450ms | 85ms | **~81% faster** |
| Create developer with task assignment | 900ms | 510ms | **~43% faster** |
| Get paginated developers | 220ms | 140ms | **~36% faster** |
| Memory usage under 100 concurrent users | 450MB | 290MB | **~35% reduction** |

---
## Garbage Collection (GC) Behavior

### GC Algorithm Used
- G1GC (`-XX:+UseG1GC`)

### JVM Flags
-Xms512m

-Xmx512m

-XX:+UseG1GC

-Xlog:gc*:file=gc-g1gc.log


### GC Metrics Summary

| Metric              | Observation         |
|---------------------|---------------------|
| Avg Pause Time      | ~73ms               |
| GC Frequency        | Moderate, healthy   |
| Full GC Count       | 0                   |
| Max Heap Usage      | ~300MB              |
| Post-GC Heap Usage  | ~70MB               |

### Conclusion
- G1GC is performing efficiently.
- No signs of memory leaks.
- App maintains stable heap usage under load.


---

## 📍Conclusion

Optimization efforts greatly enhanced the performance and scalability of the Project Tracker API. Future improvements can include:
- Asynchronous processing for bulk actions
- Implementing Redis for high-demand caching
- Distributed tracing with Zipkin or Sleuth

---

