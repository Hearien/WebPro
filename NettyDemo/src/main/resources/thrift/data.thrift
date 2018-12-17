namespace java com.hearien.thrift

typedef i16 short
typedef i32 int
typedef i64 long
typedef string String
typedef bool boolean

struct Person{
    1: optional String userName,
    2: optional int age,
    3: optional boolean marrief
}

exception DataException{
    1: optional String message,
    2: optional String callStack,
    3: optional String date
}

service PersonService{
    Person getPersonByUserName(1: required String userName) throws (1: DataException dataExeption),
    void savePerson(1: required Person person) throws (1: DataException dataExeption)
}