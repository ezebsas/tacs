angular.module('tacsthree.characters')
.controller('charactersCtrl', function($scope){
  $scope.personajes= characts;

}); 
var characts = 
  [
    {
      "id": "57424d223e7ef80f375d1f38",
      "idMarvel": 1009679,
      "name": "Tyger Tiger",
      "description": "",
      "modified": 0,
      "resourceURI": "http://gateway.marvel.com/v1/public/characters/1009679",
      "urls": [],
      "thumbnailUrl": "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available/standard_amazing.jpg",
      "comics": [],
      "stories": [],
      "events": [],
      "series": []
    },
    {
      "id": "57424d223e7ef80f375d1f36",
      "idMarvel": 1009678,
      "name": "Tusk",
      "description": "",
      "modified": 0,
      "resourceURI": "http://gateway.marvel.com/v1/public/characters/1009678",
      "urls": [],
      "thumbnailUrl": "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available/standard_amazing.jpg",
      "comics": [
        "X-Factor (1986) #65",
        "X-Factor (1986) #66",
        "X-Factor (1986) #67",
        "X-Men (1991) #21",
        "X-Men (1991) #23"
      ],
      "stories": [
        "Endgame Part 1: Malign Influences",
        "Endgame Part 2: Heroic Effort",
        "Endgame Part 3: Lunar Opposition",
        "The Puzzle Box",
        "Comes the Time of Testing...",
        "Leaning Towards Oneself"
      ],
      "events": [],
      "series": [
        "X-Factor (1986 - 1998)",
        "X-Men (1991 - 2001)"
      ]
    }
  ];
