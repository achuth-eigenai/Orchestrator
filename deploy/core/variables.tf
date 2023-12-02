variable "subnets" {
  default = [
    "a",
    "b",
    "c",
  ]
}
variable "company_camel" {}
variable "company_small" {}
variable "stage" {
  default = "dev"
}
variable "region" {
  description = "deploy region"
  type = string
  default = "ap-southeast-1" # Singapore - refer https://aws.amazon.com/about-aws/global-infrastructure/regions_az/
}
