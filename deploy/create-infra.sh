#!/bin/bash
function main() {
  printf "STAGE: [%s]\n" "${STAGE}"
  printf "REGION: [%s]\n" "${REGION}"
  aws cloudformation --region ${REGION} deploy --template-file ./vpc-infra.json --stack-name eigenai-infra-stack-${STAGE} --parameter-overrides Stage=${STAGE} SubnetIds=eigenai-subnets VpcId=eigenai-vpc --profile=${STAGE} --capabilities CAPABILITY_NAMED_IAM
}
function help() {
  printf "MAIN: Required Params are:\n"
  printf "MAIN: -s <STAGE>\n"
  printf "MAIN: -r <REGION>\n"
}
if (($# == 0)); then
  help
  exit 1
fi
while getopts ":s:r:h" arg; do
  case $arg in
  s) # stage
    export STAGE=${OPTARG}
    ;;
  r) # region
    export REGION=${OPTARG}
    ;;
  *) # help menu
    help
    exit 0
    ;;
  esac
done

main