SELECT
    CASE
        WHEN month(DIFFERENTIATION_DATE) <= 3 THEN '1Q'
        WHEN month(DIFFERENTIATION_DATE) <= 6 THEN '2Q'
        WHEN month(DIFFERENTIATION_DATE) <= 9 THEN '3Q'
        ELSE '4Q'
        END AS QUARTER,
    COUNT(*) AS ECOLI_COUNT
FROM ECOLI_DATA
GROUP BY QUARTER
ORDER BY QUARTER;