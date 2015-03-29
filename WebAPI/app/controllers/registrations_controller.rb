require 'securerandom'

class RegistrationsController < Devise::RegistrationsController
  layout 'login', only: [:new]

  private
    def sign_up_params
      params['user']['robot_pass'] = SecureRandom.hex(4)
      params.require(:user).permit(:name, :password, :password_confirmation, :robot_pass)
    end

    def account_update_params
      params.require(:user).permit(:name, :password, :password_confirmation, :current_password)
    end

end
