SELECT COUNT(T.ID) AS res
FROM (SELECT DISTINCT ID FROM tmp) AS T;