module ApplicationHelper

  def difficulty_text(difficulty_num)
    difficulties = BuddyBot::Application.config.difficulties
    difficulties.select{|x| x[1] == difficulty_num}[0][0]
  end

end
