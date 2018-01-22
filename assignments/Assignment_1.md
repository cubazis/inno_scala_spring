## Individual project - part 1 [1 week]
We start by creating a small web-server using [scalatra](http://scalatra.org/), as a lot of sings goes around web today. Goals to learn:
- How to manage projects with [SBT](https://www.scala-sbt.org/)
- Working with JSON serialization/deserialization
- Be familiar with scalatra, to be able to create own web-services

### Deadline
Upload your report into moodle before 3:14 AM, 1 February 
* Note: 3:14 of night from Wednesday to Thursday.

### Task overview
Build a web-server that has routes (addresses that you can access, for example: `vk.com\` ):
```
POST \messages
    Pass here a JSON that contains id and message that would be created
    Example of requst:
    curl --request POST \
        --url http://0.0.0.0:3000/messages/ \
        --header 'Content-Type: application/json' \
        --data '{
	    "id" : 1,
	    "text" : "Test text"
        }'
        
GET \messages
    It should return created messages
    Example of request:
    curl --request GET \
        --url http://0.0.0.0:3000/messages/
        
GET \messages\:id
    It should return only one message that has same id as :id parameter
    Example of request:
    curl --request GET \
        --url http://0.0.0.0:3000/messages/1
        
PUT \messages\:id
    It should update message with id the same as :id parameter
    Example of request:
    curl --request PUT \
      --url http://0.0.0.0:3000/messages/1 \
      --header 'Content-Type: application/json' \
      --data '{
	    "text" : "Some new text"
      }'
      
DELETE \messages\:id
    It should delete a message with id the same as :id parameter
    Example of request:
    curl --request DELETE \
        --url http://0.0.0.0:3000/messages/1
```
**Do not use persistent storage**, store everything in the memory, but if you want, you can make it your additional assignment.

### Criteria of done
* Code on [github](https://github.com/)
* Project could be built using instructions in your `README.md` in repository
* Make a small report that points out what was done (which functionality is working), not more then 1 page A4. Add link to your github repo into report.
    * If you haven't managed something to work please write why, so we can cover this material during the course.
    * [Optional] If you used some additional tools/materials/guides, please mention them in the report, it could be helpful for other students and try to connect the problem you was solving and material that you was using.
