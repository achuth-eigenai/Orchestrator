#!/bin/bash
function main() {
  version=`date +%y%m%d%H%M%S`
  printf "REGION: [%s]\n" "${REGION}"
  printf "ACCOUNT: [%s]\n" "${ACCOUNT}"
  printf "STAGE: [%s]\n" "${STAGE}"
  gradle -Pstage=${STAGE} clean build -p ../.. || exit 1
  printf "Identified Release Version: [%s]\n" "${version}"
  aws ecr get-login-password --region ${REGION} --profile ${STAGE}  | docker login --username AWS --password-stdin ${ACCOUNT}.dkr.ecr.${REGION}.amazonaws.com || exit 1
  docker build -t moversly-procyon-${STAGE} ../.. || exit 1
  docker tag moversly-procyon-${STAGE}:latest ${ACCOUNT}.dkr.ecr.${REGION}.amazonaws.com/moversly-procyon-${STAGE}:${version} || exit 1
  docker push ${ACCOUNT}.dkr.ecr.${REGION}.amazonaws.com/moversly-procyon-${STAGE}:${version} || exit 1
  aws cloudformation --region ${REGION} deploy --template-file ../templates/ecs-infra.json --stack-name ecs-infra-${STAGE} --parameter-overrides Stage=${STAGE} AccountId="${ACCOUNT}" SubnetId="moversly-subnets-${STAGE}" VpcId="moversly-vpc-${STAGE}" KeyName="moversly-key-pair" Version="$version" --profile ${STAGE} --capabilities CAPABILITY_NAMED_IAM
}

function help() {
  printf "MAIN: Required Params are:\n"
  printf "MAIN: -r <REGION>\n"
  printf "MAIN: -a <ACCOUNT>\n"
  printf "MAIN: -s <STAGE>\n"
}
function delete_old_tags() {
  count=`git tag -l | wc -l`
  keep=20
  num=0

  for t in `git tag -l --sort=taggerdate`
  do
    if [ "$num" -ge `expr $count - $keep` ]
      then
        break
    fi

    git push origin :$t
    git tag -d $t

    num=`expr $num + 1`
    echo "[$num] Removed $t"
  done
}
if (($# == 0)); then
  help
  exit 1
fi
while getopts ":a:r:s:g:h" arg; do
  case $arg in
  r) export REGION=${OPTARG};;
  a) export ACCOUNT=${OPTARG};;
  s) export STAGE=${OPTARG};;
  *) help
    exit 0;;
  esac
done

main
