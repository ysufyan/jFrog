# jFrog

An API is created that calls twp endpoints exposed by jFrog. API retruns JSON mentioning two most poplar atifcats.

API Call:
http://127.0.0.1:8080/jFrogAssessment/api/search/two-mostly-downloaded?repo=jcenter-cache

API Response:
[
    {
        "repo": "jcenter-cache",
        "path": "asm/asm-parent/3.3",
        "name": "asm-parent-3.3.pom",
        "type": "file",
        "size": 4330,
        "created": 1555971938975,
        "createdBy": "anonymous",
        "modified": 1286370408000,
        "modifiedBy": "anonymous",
        "updated": "2019-04-22T22:25:38.976Z"
    },
    {
        "repo": "jcenter-cache",
        "path": "org/hamcrest/hamcrest-core/1.3",
        "name": "hamcrest-core-1.3.pom",
        "type": "file",
        "size": 766,
        "created": 1555971937843,
        "createdBy": "anonymous",
        "modified": 1341868082000,
        "modifiedBy": "anonymous",
        "updated": "2019-04-22T22:25:37.844Z"
    }
]

Technology Stack:
- Jakarta EE 8
- JDK 11
- Wildfly Application server. 18.0.1
- Eclipse IDE. 2019-12 R
- JAX-RS. RestEasy 4.4.1.Final
- Postman
