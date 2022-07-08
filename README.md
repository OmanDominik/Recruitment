# Recruitment

## Api documentation

http://localhost:9090/swagger-ui.html


## Sample endpoint calls

### Forst endpoint '/basicData/{size}' contains ‘type, _id, name, type, latitude, longitude’

http://localhost:9090/basicData/3

### Third endpoint '/specifiedData/{size}' 

http://localhost:9090/specifiedData/4?params=latitude,longitude 

### Fourth endpoint '/mathematicalOperations'

http://localhost:9090/mathematicalOperations?operations=latitude*longitude,T(Math).sqrt(location_id),distance-name,key-(-_id)