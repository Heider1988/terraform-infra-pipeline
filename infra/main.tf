resource "aws_s3_bucket" "bucket" {
  bucket = "${var.bucket_name}-${random_string.bucket_suffix.result}"
}

resource "random_string" "bucket_suffix" {
  length  = 8
  special = false
  upper   = false
}