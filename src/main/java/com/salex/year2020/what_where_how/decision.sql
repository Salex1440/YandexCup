
WITH HD AS (
SELECT
qrtz_log.host_name,
qrtz_log.job_name AS jn,
max(strftime('%s', job_finished_time) - strftime('%s', trigger_fire_time)) as max_host_duration
FROM qrtz_log
GROUP BY qrtz_log.host_name, qrtz_log.job_name
)

SELECT job_name, qrtz_log.host_name, trigger_fire_time, job_finished_time,
IIF(job_status = 'OK', strftime('%s', job_finished_time) - strftime('%s', trigger_fire_time), 0) AS duration,
-- 0 AS duration_diff,
job_status,
HD.max_host_duration
FROM qrtz_log
JOIN HD ON HD.host_name = qrtz_log.host_name
WHERE job_status IS NOT NULL OR (strftime('%s', '2020-09-01 10:00:00') - strftime('%s', trigger_fire_time) > 2 * HD.max_host_duration)
GROUP BY job_name
HAVING max(trigger_fire_time)
;

