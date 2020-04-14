# AWS ECR(Elastic Container Registry)

- Docker 컨테이너 이미지를 손쉽게 저장
- 관리 및 배포할 수 있게 해주는 완전관리형 Docker 컨테이너 레지스트리 
- AWS ECS(Elastic container service)와 결합하여 EC2 인스턴스에 컨테이너 배포까지 통합 지원
- docker hub가 public repo라면, ECR은 private repo

# AWS ECS(Elastic Container Service)

- container orchestration(like k8s)
- 도커 컨테이너 서비스에 대한 배포 및 관리 
- AWS ECS는 AWS EC2 인스턴스를 기반으로 클러스터링을 위한 서버로 구축되며 Auto scaling group을 통해 자동 확장, 제거 가능


# AWS EKS(Elastic Kubernetes Service)

- Amazon Elastic Kubernetes Service(Amazon EKS)는 Kubernetes 제어 플레인을 설치하고 운영할 필요 없이 AWS에서 Kubernetes를 손쉽게 실행하도록 하는 관리형 서비스입니다. Kubernetes는 컨테이너화된 애플리케이션의 배포, 조정 및 관리 자동화를 위한 오픈 소스 시스템

- 여러 가용 영역에서 Kubernetes 제어 플레인 인스터스를 실행하여 고가용성
- 비정상 제어 플레인 인스턴스를 자동으로 감지하고 교체 
- 여러 AWS 서비스와 통합되어 다음을 포함한 애플리케이션에 대한 확장성과 보안을 제공
- Amazon EKS는 오픈 소스 Kubernetes 소프트웨어의 최신 버전을 실행하므로 Kubernetes 커뮤니티에서 모든 기존 플러그인과 도구를 사용할 수 있습니다. 온프레미스 데이터 센터에서 실행 중인지 퍼블릭 클라우드에서 실행 중인지에 상관없이, Amazon EKS에서 실행 중인 애플리케이션은 표준 Kubernetes 환경에서 실행 중인 애플리케이션과 완벽하게 호환됩니다. 즉, 필요한 코드를 수정하지 않고 표준 Kubernetes 애플리케이션을 Amazon EKS로 쉽게 마이그레이션 가능