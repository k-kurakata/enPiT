class ApplicationController < ActionController::Base
  # Prevent CSRF attacks by raising an exception.
  # For APIs, you may want to use :null_session instead.
  protect_from_forgery with: :exception
  before_action :authenticate_user!, only: :dashboard
  before_action :set_user

  def dashboard
    @missions = Mission.where(user_id: @user.id, closed: false)
  end

  private
    def set_user
      @user = current_user
    end

end
