variable "company_small" {
  type = string
  default = "eigenai"
}
variable "company_camel" {
  type = string
  default = "EigenAI"
}
variable "stage" {
  default = "dev"
}
variable "account" {
  default = 828109562156
}
variable "region" {
  description = "deploy region"
  type = string
  default = "ap-southeast-1" # Singapore - refer https://aws.amazon.com/about-aws/global-infrastructure/regions_az/
}

