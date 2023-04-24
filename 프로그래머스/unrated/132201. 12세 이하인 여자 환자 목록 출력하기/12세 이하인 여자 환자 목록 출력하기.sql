-- 코드를 입력하세요
-- 12세 이하
-- 여자
-- 전화번호 없을 경우 NONE
-- 나이 내림차순, 환자이름 오름차순
SELECT PT_NAME, PT_NO, GEND_CD, AGE, CASE WHEN TLNO IS NULL THEN 'NONE' ELSE TLNO END AS TLNO
FROM PATIENT
WHERE AGE <= 12 AND GEND_CD = 'W'
ORDER BY AGE DESC, PT_NAME;
                                      