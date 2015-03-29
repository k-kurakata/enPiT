class Product < ActiveRecord::Base
  has_many :purchased_products
  has_many :users, :through => :purchased_products
end
