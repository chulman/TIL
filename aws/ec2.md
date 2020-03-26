# EC2
- AWS에서 제공하는 가상 서버(Virtual Machine, VM)
- Amazon Linux, Cent OS, Ubuntu, Windows 등 다양한 OS 제공 •원하는 사양을 관리자가 직접 선택할 수 있음
- 필요할 때마다 사용 / 중지가 가능


### Amazon Linux2  

- AWS에서 제공하는 차세대 Linux 운영 체제
- Amazon Linux Extras 리포지토리를 통해 최신 소프트웨어 패키지 제공 
- Container 환경에 최적화 된 OS

### EC2 환경에서 패키지 매니저를 통한 Nginx 설치
```shell script
sudo su

amazon-linux-extras install nginx1.12 

nginx

```

### AWS AMI

- 서버 인스턴스의 기반이 되는 이미지
- 윈도우 CD, iso와 같은 개념
- Amazon에서 제공하는 빅데이터, 웹서비스 등 각 기능 별로 빌드구워낸 한 이미지를 사용하거나 다른 사람이 공유하는 이미지도 사용 가능


### EC2 접속

```shell script
sudo ssh -i {pem 파일} ec2-user@{DNS 주소}
``` 

- ssh 키를 통해 서버에 저장 된 Public Key와 사용자의 Private Key를 비교하여 확인됬을 때만 접속을 허용