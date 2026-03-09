# Ticket-api
🎯 Git Convention
🎉 Start: Start New Project [:tada:]
✨ Feat: 새로운 기능을 추가 [:sparkles:]
🐛 Fix: 버그 수정 [:bug:]
🎨 Design: CSS 등 사용자 UI 디자인 변경 [:art:]
♻️ Refactor: 코드 리팩토링 [:recycle:]
🔧 Settings: Changing configuration files [:wrench:]
🗃️ Comment: 필요한 주석 추가 및 변경 [:card_file_box:]
➕ Dependency/Plugin: Add a dependency/plugin [:heavy_plus_sign:]
📝 Docs: 문서 수정 [:memo:]
🔀 Merge: Merge branches [:twisted_rightwards_arrows:]
🚀 Deploy: Deploying stuff [:rocket:]
🚚 Rename: 파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우 [:truck:]
🔥 Remove: 파일을 삭제하는 작업만 수행한 경우 [:fire:]
⏪️ Revert: 전 버전으로 롤백 [:rewind:]
🪴 Branch Convention (GitHub Flow)

main: 배포 가능한 브랜치, 항상 배포 가능한 상태를 유지
feature/{description}: 새로운 기능을 개발하는 브랜치
예: feature/social-login
브랜치 공유 X → 특수한 경우 팀원들에게 알리기
팀원이 짠 코드 리뷰 없이 수정 X → 수정 시 PR 남기고 리뷰 필수
ex) 본인이 정의한 클래스 아닌 곳에서 코드 작성 시 팀원들과 논의
Flow

develop 브랜치에서 새로운 브랜치를 생성. → develop 브랜치는 main 브랜치에서 생성
작업을 완료하고 커밋 메시지에 맞게 커밋 후 푸시.
develop 으로 병합 시 Pull Request를 생성 / 팀원들의 리뷰.
리뷰가 완료되면 develop 브랜치로 병합.
병합 후, 배포 필요 시 main 브랜치로 Pull Request를 생성 / 팀원들의 리뷰 진행
병합 후 배포.
예시:

# 새로운 기능 개발
git checkout -b feature/social-login

# 작업 완료 후, main 브랜치로 병합
git checkout main
git pull origin main
git merge feature/social-login
git push origin main
🐋 로컬에서 Docker 로 빌드하기 🐋
Docker Desktop 실행
Build 하기 전 spotless 적용 ./gradlew spotlessApply
정해진 컨벤션을 지키고 있는지 검사 (spotelesscheck) ./gradlew spotlessCheck
spring boot build ./gradlew build
Docker Desktop 실행중 인지 확인 docker info
Docker로 빌드하기 docker build -t "본인 dockerhub repo 이름"/helfoome . 
Docker Hub login(IDE terminal에서 실행) docker login
Docker Image Tag docker tag "본인 dockerhub repo 이름"/helfoome "본인 dockerhub repo 이름"/helfoome:latest
Docker Image Push docker push "본인 dockerhub repo 이름"/helfoome:latest
🐋 Docker 실행중인 컨테이너 중지하기 🐋
실행 중인 컨테이너 목록 확인 docker ps
컨테이너 중지 실행 중인 컨테이너의 CONTAINER ID를 찾아서 중지. docker stop <CONTAINER ID>
컨테이너의 삭제가 필요할 경우 docker rm <CONTAINER ID>
이미지 삭제가 필요할 경우 docker rmi <IMAGE ID or IMAGE NAME>
🔧 Submodule convention
submodule 수정 → 팀원들에게 알리기
submodule 경로로 이동 후 main으로 push (브랜치 생성 X)
루트 경로로 나와서 해당 브랜치로 push
commit message : "submodule push"로 통일
git submodule update --remote로 서브모듈 최신화
루트 경로에서 바뀐 내용 헤당 브랜치로 push
commit message : "submodule latest"로 통일
