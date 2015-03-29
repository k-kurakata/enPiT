class User < ActiveRecord::Base
  # Include default devise modules. Others available are:
  # :confirmable, :lockable, :timeoutable and :omniauthable
  # :recoverable
  devise :database_authenticatable, :registerable,
         :rememberable, :trackable, :validatable
  def email_required?
    false
  end

  has_many :purchased_products
  has_many :products, :through => :purchased_products
  validates :name, presence: true, uniqueness: true
end
