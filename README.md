# EnjoyTrip_spring_구미_5반_이우성_신창엽



## Users

### Join
![join](/uploads/32b04c44753dea6f5bf991b3e4020a6b/join.PNG)
회원로그인

### check-id
![check-id](/uploads/dd56fa020923699245148ccc32165622/check-id.PNG)
아이디는 unique로 설정하였기 때문에 중복검사 기능을 추가하였습니다.

### check-nickname
![check-nickname](/uploads/022b44bf924cf9cb73b618eb2b7069c8/check-nickname.PNG)
닉네임은 unique로 설정하였기 때문에 중복검사 기능을 추가하였습니다.

### login

### logout

### user-info

### new-password
![new-password](/uploads/06a68a692f99fb5c12519fc3388e1209/new-password.PNG)
비밀번호는 개인 정보이기때문에 BCrypt로 저장을 하였습니다.
BCrypt는 decoding이 되지 않기 때문에 새로운 비밀번호를 수정할 수 있도록 하였습니다.
특히 유저 정보 중 수정 할 수 있는것은 비밀번호만 설정하였습니다.

### user-delete

## Attraction

### all
![map-all](/uploads/9e8e12638e60c64ccb5e4be72be3c050/map-all.PNG)
지역과 타입을 지정해 주면 정보가 나올 수 있도록 하였습니다.

### name
![map-name](/uploads/00964f927a042ca8d3f90495821cbd5d/map-name.PNG)
지역과 이름을 지정할 경우 나올 수 있도록 하였습니다.
