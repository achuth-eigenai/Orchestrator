# Deploy
Not Technical? Don't worry. It's all going to be okay, just follow this document fully. 

## Prerequisites
#### Step 1
Install terraform - refer [here](https://developer.hashicorp.com/terraform/tutorials/aws-get-started/install-cli)
#### Step 2
```shell
$ ./deploy.sh
```

# FAQ
#### I am launching into a new region or new AWS account or even recreating from scratch. What do I do?
No worries, the script will take care.

#### Eigen AI is several years old now, the Amazon AMI is deprecated and has serious security issues, I have to upgrade the ami but I am non-technical how do I find out which ami to use?
```shell
aws ec2 describe-images \
  --owners 099720109477 \
  --filters "Name=name,Values=ubuntu/images/hvm-ssd/ubuntu-focal-20.04-amd64-server-*" "Name=architecture,Values=x86_64" "Name=state,Values=available" "Name=virtualization-type,Values=hvm" "Name=root-device-type,Values=ebs" \
  --query 'Images[0].ImageId' \
  --region ap-southeast-1
```

