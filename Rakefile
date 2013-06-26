require 'json'
require 'net/http'

desc 'Create bas diretory structure and README.md for a given problem and language'
task :p, [:problem_id, :language] do |t, args|

  uri = URI "http://www.spoj.com/problems/#{args.problem_id.upcase}/"
  problem_page = Net::HTTP.get(uri)

  root = File.dirname(__FILE__)

  problem_root = "#{root}/problems/#{args.problem_id.downcase}"
  Dir::mkdir problem_root if not Dir::exists? problem_root

  # Modify the sublime-project to include the new problem.
  # If the problem is already added then do nothing.
  File.open("#{root}/spoj-kata.sublime-project", "r+") do |file|
    json = JSON.load file
    needs_write = true
    json['folders'].each do |folder|
      if folder['path'] == "problems/#{args.problem_id.downcase}"
        needs_write = false
        break
      end
    end
    if needs_write
      json['folders'].push({
        path: "problems/#{args.problem_id.downcase}",
        file_exclude_patterns: ["a.out"],
        folder_exclude_patterns: ["bin"]
      })
      file.rewind
      file.write JSON.pretty_generate(json)
    end
  end

  # Write a simple README.md with a link to the actual problem
  File.open("#{problem_root}/README.md", 'w') do |file|
    title = /<h1>(\d+\..*?)<\/h1>/.match(problem_page)[1]
    file.write "# [#{title}](#{uri})\n\n#{args.language.capitalize}"
  end

  src_dir = "#{problem_root}/src"
  Dir::mkdir src_dir if not Dir::exists? src_dir

  # Write source file according to spec in language_settings.json
  p_id = args.problem_id.downcase
  language_settings = JSON.parse(IO.read('language_settings.json'))
  if language_settings[args.language.downcase]
    lang = language_settings[args.language.downcase]

    File.open "#{src_dir}/#{lang['capitalize'] ? p_id.capitalize : p_id}.#{lang['ext']}", "w"
  end

end