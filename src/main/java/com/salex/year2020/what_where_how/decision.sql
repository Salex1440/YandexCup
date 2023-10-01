SELECT job_name, host_name, trigger_fire_time, job_finished_time,
(strftime('%s', job_finished_time) - strftime('%s', trigger_fire_time)) AS duration,
0 AS duration_diff
FROM qrtz_log
-- GROUP BY job_name
-- HAVING max(trigger_fire_time)
;