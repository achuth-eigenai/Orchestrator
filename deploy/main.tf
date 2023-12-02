terraform {
  required_providers {
    aws = {
      source = "hashicorp/aws"
      version = "~>3.0"
    }
  }
}

provider "aws" {
  region = var.region
}

module "core" {
  source = "./core"
  region = var.region
  company_camel = var.company_camel
  company_small = var.company_small
  stage = var.stage
}

module "ecs" {
  source = "./ecs"
  stage = var.stage
  vpc_id = module.core.vpc_id
  subnet_ids = module.core.subnet_ids
  company_camel = var.company_camel
  company_small = var.company_small
  region = var.region
  account=var.account
}
output "vpc_id" {
  value = module.core.vpc_id
}

output "subnet_ids" {
  value = module.core.subnet_ids
}