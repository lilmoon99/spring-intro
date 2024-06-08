1. Получить студента по ID: localhost:8080/students/{id}
2. Получить всех студентов: localhost:8080/students/all
3. Получить всех студентов группы: localhost:8080/students/group/{group_name(basic,java,typescript,python)}
4. Получить студента по имени: localhost:8080/students?name={student_name}
5. Создание студента: POST localhost:8080/students/new
   requires request body. For example:
   {
   "id":99,
   "name": "student_99",
   "groupName": "Kotlin"
   }
6. Удаление студента: DELETE localhost:8080/students/{id}