# Recruitment

## Sample endpoint calls

### First endpoint '/generate/json/{size}' 

http://localhost:8080/generate/json/5

### Second endpoint '/basicData/{size}' contains ‘type, _id, name, type, latitude, longitude’

http://localhost:8080/basicData/3

### Third endpoint '/specifiedData/{size}' 

http://localhost:8080/specifiedData/3?params=_id,latitude,name,longitude  

### Fourth endpoint '/mathematicalOperations'

http://localhost:8080/mathematicalOperations?params=latitude*longitude,sqrt(longitude)