require "net/http"
require "uri"

class MissionsController < ApplicationController
  before_action :set_difficulties, only: [:new, :edit, :create, :update]
  before_action :set_mission, only: [:show, :edit, :update, :destroy]
  before_action :authenticate_user!

  # GET /missions/1
  # GET /missions/1.json
  def show
  end

  # GET /missions/new
  def new
    @mission = Mission.new
  end

  # GET /missions/1/edit
  def edit
  end

  # POST /missions
  # POST /missions.json
  def create
    @mission = Mission.new(mission_params)
    @mission.user_id = @user.id

    respond_to do |format|
      send_mission @user.name, @mission.body

      if @mission.save
        format.html { redirect_to @mission, notice: 'Mission was successfully created.' }
        format.json { render :show, status: :created, location: @mission }
      end
      format.html { render :new }
      format.json { render json: @mission.errors, status: :unprocessable_entity }
    end
  end

  # PATCH/PUT /missions/1
  # PATCH/PUT /missions/1.json
  def update
    respond_to do |format|
      if @mission.update(mission_params)
        format.html { redirect_to @mission, notice: 'Mission was successfully updated.' }
        format.json { render :show, status: :ok, location: @mission }
      else
        format.html { render :edit }
        format.json { render json: @mission.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /missions/1
  # DELETE /missions/1.json
  def destroy
    @user.update({ :point => @user.point + @mission.difficulty })
    @mission.update({ :closed => true })
    send_mission @user.name, ' '
    respond_to do |format|
      format.html { redirect_to root_url, notice: 'Mission was successfully closed.' }
      format.json { head :no_content }
    end
  end

  private
    def set_difficulties
      @difficulties = BuddyBot::Application.config.difficulties
    end

    # Use callbacks to share common setup or constraints between actions.
    def set_mission
      @mission = Mission.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def mission_params
      params.require(:mission).permit(:body, :difficulty, :closed)
    end

    def send_mission(user_name, mission_body)
      params = URI.encode_www_form([["id", user_name], ["contentsdata", mission_body]])
      uri = URI.parse("http://54.65.61.46:8080/ServiceSample/contentsupload?#{params}")
      res = Net::HTTP.get_response(uri)
      logger.debug("uri = #{uri}")
      logger.debug("res = #{res}")
      return res
    end
end
