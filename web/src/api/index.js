import request from '../api/request'

// 考试接口 [SDD §6.2]
export const apiExamStart = (sessionId) => request.post('/exam/start', null, { params: { sessionId } })
export const apiExamNext = (recordId) => request.get('/exam/next', { params: { recordId } })
export const apiExamAnswer = (data) => request.post('/exam/answer', null, { params: data })
export const apiExamFinish = (recordId) => request.post('/exam/finish', null, { params: { recordId } })
export const apiExamResume = (recordId) => request.get('/exam/resume', { params: { recordId } })

// 报告接口 [SDD §6.3]
export const apiReportProfile = (userId) => request.get(`/report/profile/${userId}`)
export const apiReportDetail = (recordId) => request.get(`/report/detail/${recordId}`)
export const apiReportHistory = () => request.get('/report/history')
export const apiReportSubordinates = () => request.get('/report/subordinates')
