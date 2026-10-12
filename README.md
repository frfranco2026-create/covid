
# Restful APIs

Get all covid records
`curl http://localhost:8080/covid`

Get covid record by id
`curl http://localhost:8080/covid/{id}`

Get top5 covid records by field
`curl 'http://localhost:8080/covid/top5?by=active'`