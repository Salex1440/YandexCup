SELECT job_name, host_name, trigger_fire_time, job_finished_time,
IIF(job_status = 'OK',
 (strftime('%s', job_finished_time) - strftime('%s', trigger_fire_time)), 0) AS duration,
0 AS duration_diff,
job_status
FROM qrtz_log
-- GROUP BY job_name
-- HAVING max(trigger_fire_time)
;