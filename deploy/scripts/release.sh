#!/bin/bash
function main() {
  set -x
  version=`date +%y%m%d%H%M%S`
  REGION=ap-southeast-1
  ACCOUNT=828109562156
  STAGE=prod
  printf "REGION: [%s]\n" "${REGION}"
  printf "ACCOUNT: [%s]\n" "${ACCOUNT}"
  printf "STAGE: [%s]\n" "${STAGE}"
  gradle -Pstage=${STAGE} clean build -p ../.. --stacktrace || exit 1
  printf "Identified Release Version: [%s]\n" "${version}"
  aws ecr get-login-password --region ${REGION} --profile ${STAGE}  | docker login --username AWS --password-stdin ${ACCOUNT}.dkr.ecr.${REGION}.amazonaws.com || exit 1
  docker build -t eigenai-orchestrator-${STAGE} ../.. || exit 1
  docker tag eigenai-orchestrator-${STAGE}:latest ${ACCOUNT}.dkr.ecr.${REGION}.amazonaws.com/eigenai-orchestrator-${STAGE}:${version} || exit 1
  docker push ${ACCOUNT}.dkr.ecr.${REGION}.amazonaws.com/eigenai-orchestrator-${STAGE}:${version} || exit 1
}

main
